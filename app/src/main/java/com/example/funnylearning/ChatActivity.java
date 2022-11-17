package com.example.funnylearning;

import static java.util.Collections.singletonList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.funnylearning.databinding.ActivityChatBinding;
import com.getstream.sdk.chat.utils.Utils;

import io.getstream.chat.android.client.ChatClient;
import io.getstream.chat.android.client.api.models.FilterObject;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

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
        ChatClient client = new ChatClient.Builder("7tbut32y3u75", getApplicationContext())
                .withPlugin(streamOfflinePluginFactory)
                .logLevel(ChatLogLevel.ALL) // Set to NOTHING in prod
                .build();


        // Step 3 - Authenticate and connect the user
        User user = new User();
        user.setId("John");
        user.setName("John Cena");
        user.setImage("https://bit.ly/2TIt8NR");

        String token = client.devToken(user.getId());
        client.connectUser(
                user,token
        ).enqueue();

        // Step 4 - Set the channel list filter and order
        // This can be read as requiring only channels whose "type" is "messaging" AND
        // whose "members" include our "user.id"

        FilterObject filter = Filters.and(
                Filters.eq("type", "messaging"),
                Filters.in("members", singletonList(user.getId()))
        );

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

        // Step 5 - Connect the ChannelListViewModel to the ChannelListView, loose
        //          coupling makes it easy to customize
        ChannelListViewModelBinding.bind(channelsViewModel, binding.channelListView, this);
        binding.channelListView.setChannelItemClickListener(channel -> {
            startActivity(ChannelActivity.newIntent(this, channel));
        });


    }

}