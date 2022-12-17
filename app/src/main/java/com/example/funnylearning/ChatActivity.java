package com.example.funnylearning;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

import com.example.funnylearning.Bean.model.UserData;
import com.example.funnylearning.Database.UserDataDao;
import com.example.funnylearning.databinding.ActivityChatBinding;
import com.getstream.sdk.chat.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.getstream.chat.android.client.ChatClient;
import io.getstream.chat.android.client.api.models.FilterObject;
import io.getstream.chat.android.client.channel.ChannelClient;
import io.getstream.chat.android.client.logger.ChatLogLevel;
import io.getstream.chat.android.client.models.Channel;
import io.getstream.chat.android.client.models.Filters;
import io.getstream.chat.android.client.models.Message;
import io.getstream.chat.android.client.models.User;
import io.getstream.chat.android.offline.model.message.attachments.UploadAttachmentsNetworkType;
import io.getstream.chat.android.offline.plugin.configuration.Config;
import io.getstream.chat.android.offline.plugin.factory.StreamOfflinePluginFactory;
import io.getstream.chat.android.ui.TransformStyle;
import io.getstream.chat.android.ui.channel.ChannelListActivity;
import io.getstream.chat.android.ui.channel.ChannelListFragment;
import io.getstream.chat.android.ui.channel.list.ChannelListView;
import io.getstream.chat.android.ui.channel.list.header.viewmodel.ChannelListHeaderViewModel;
import io.getstream.chat.android.ui.channel.list.header.viewmodel.ChannelListHeaderViewModelBinding;
import io.getstream.chat.android.ui.channel.list.viewmodel.ChannelListViewModel;
import io.getstream.chat.android.ui.channel.list.viewmodel.ChannelListViewModelBinding;
import io.getstream.chat.android.ui.channel.list.viewmodel.factory.ChannelListViewModelFactory;
import io.getstream.chat.android.ui.message.MessageListActivity;
import io.getstream.chat.android.ui.message.MessageListFragment;
import io.getstream.chat.android.ui.search.SearchInputView;
import io.getstream.chat.android.ui.search.list.viewmodel.SearchViewModel;
import io.getstream.chat.android.ui.search.list.viewmodel.SearchViewModelBinding;
import io.getstream.chat.android.ui.search.list.SearchResultListView;



public class ChatActivity extends AppCompatActivity
{
    int userId = 0;
    UserDataDao userDataDao = new UserDataDao(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Bundle extra = getIntent().getExtras();
        if(extra != null){
            userId = extra.getInt("userId");
        }else{
            Toast.makeText(this, "Id not passed", Toast.LENGTH_SHORT).show();
        }

        UserData userData = null;
        userData = userDataDao.getUserData(userId);


        // Step 0 - inflate binding
        ActivityChatBinding binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Step 1 - Set up the OfflinePlugin for offline storage
        StreamOfflinePluginFactory streamOfflinePluginFactory = new StreamOfflinePluginFactory(
                new Config(
                        true,
                        true,
                        true,
                        UploadAttachmentsNetworkType.NOT_ROAMING
                ),
                getApplicationContext()
        );



        // Step 2 - Set up the client for API calls with the plugin for offline storage
        ChatClient client = new ChatClient.Builder("pghceqswub3e", getApplicationContext())
                .withPlugin(streamOfflinePluginFactory)
                .logLevel(ChatLogLevel.ALL) // Set to NOTHING in prod
                .build();


        // Step 3 - Authenticate and connect the user
        User user = new User();
        user.setId((Integer.toString(userData.getUserId())));
        user.setName(userData.getName());
        user.setImage("https://bit.ly/2TIt8NR");

        String token = client.devToken(user.getId());
        client.connectUser(
                user,token
        ).enqueue(result -> {
            if (result.isSuccess()) {
                // Logged in
                User userRes = result.data().getUser();
                String connectionId = result.data().getConnectionId();

                ChannelClient channelClient = client.channel("messaging", "General");

                Map<String, Object> extraData = new HashMap<>();
                List<String> memberIds = new LinkedList<>();

                memberIds.add(user.getId());
                extraData.put("name","General");
                extraData.put("image","https://bit.ly/2TIt8NR");


                channelClient.addMembers(memberIds, null).enqueue(result1 -> {
                    if (result1.isSuccess()) {
                        Channel channel = result1.data();
                    } else {
                        System.out.println("Channel failed");
                    }
                });

                /*channelClient.create(memberIds, extraData)
                        .enqueue(result1 -> {
                            if (result1.isSuccess()) {
                                Channel newChannel = result1.data();
                            } else {
                                System.out.println("Channel failed");
                            }
                        });*/

                /*client.createChannel("messaging", "general", memberIds, extraData).enqueue(result1 -> {
                    if (result1.isSuccess()) {
                        Channel channel = result1.data();
                    } else {
                        System.out.println("Channel failed");
                    }
                });*/

            } else {
                System.out.println("Connection failed");
            }
        });


        // Step 4 - Set the channel list filter and order
        // This can be read as requiring only channels whose "type" is "messaging" AND
        // whose "members" include our "user.id"

        FilterObject filter = Filters.and(
                Filters.eq("type", "messaging"),
                Filters.in("members", singletonList(user.getId()))
        );


        // Step 5 - Connect the ChannelListViewModel to the ChannelListView, loose
        //          coupling makes it easy to customize
        ViewModelProvider.Factory factory = new ChannelListViewModelFactory.Builder()
                .filter(filter)
                .sort(ChannelListViewModel.DEFAULT_SORT)
                .build();

        //this.startActivity(ChannelListActivity.createIntent(this));

        ChannelListHeaderViewModel headerViewModel =
                new ViewModelProvider(this).get(ChannelListHeaderViewModel.class);

        SearchViewModel searchViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);

        ChannelListViewModel channelsViewModel =
                new ViewModelProvider(this, factory).get(ChannelListViewModel.class);

        // Bind it with ChannelListHeaderView
        ChannelListHeaderViewModelBinding.bind(headerViewModel, binding.channelListHeaderView, this);

        //SearchViewModel viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        // Bind it with SearchResultListView
        //SearchViewModelBinding.bind(searchViewModel, binding.searchResultListView, this);
        // Notify ViewModel when search is triggered
        //binding.searchInputView.setSearchStartedListener(searchViewModel::setQuery);

        //SearchViewModelBinding.bind(searchViewModel, binding.searchResultListView, this);
        /*binding.searchInputView.setDebouncedInputChangedListener(query -> {
            // Search query changed and has been stable for a short while
            if (query.isEmpty()) {
                binding.channelListView.setVisibility(ChannelListView.VISIBLE);
            }
        });

        // Notify ViewModel when search is triggered
        binding.searchInputView.setSearchStartedListener(query->{
            searchViewModel.setQuery(query);
            if (query.isEmpty()){
                binding.channelListView.setVisibility(ChannelListView.VISIBLE);
            }
            if (!query.isEmpty()){
                binding.searchResultListView.setVisibility(SearchResultListView.VISIBLE);
            }
        });*/

        ChannelListViewModelBinding.bind(channelsViewModel, binding.channelListView, this);
        binding.channelListView.setChannelItemClickListener(channel -> {
            startActivity(ChannelActivity.newIntent(this, channel));
        });

    }

}