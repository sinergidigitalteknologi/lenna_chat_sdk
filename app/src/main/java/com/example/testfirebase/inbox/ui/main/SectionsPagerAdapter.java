package com.example.testfirebase.inbox.ui.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.testfirebase.NotifFragment;
import com.example.testfirebase.R;

import ai.lenna.lennachatmodul.Chat;
import ai.lenna.lennachatmodul.chatRoom.ChatRoomLennaFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Fragment fragment = null;
        switch (position) {
            case 0:
                Bitmap botRoom = BitmapFactory.decodeResource(mContext.getResources(),
                        R.drawable.icon_bot_room);

                Bitmap agentRoom = BitmapFactory.decodeResource(mContext.getResources(),
                        R.drawable.icon_agent_room);

                Bitmap iconLogo = BitmapFactory.decodeResource(mContext.getResources(),
                        R.drawable.icon_logo);

                Chat.setAppId("1yoD5Y");
                Chat.setBotId("zbq2dp");
                Chat.setAppKey("gm+sCaMg5ai0vPes+tB83O3G0vaS4ahwGV+81Hnzr6jCbi7g+vYbmzHHcy/vH64jHMwq9pLr8z/eWXfVWZ4gPv64p6PvmW4aHWbnIfpF9SeKSRJGy+pXyMbiqBdzpOEurDhsLixpHvA21sUqlHPq71XJxLoNg9hPhWSfCexpzCh36OlnW1hpoX7YSNGVDRUtorCBcPerj/43UQVfeKCA+Q==");
                Chat.setUserName("ar_dev");
//                Chat.setIcon(R.drawable.icon_logo);
//                Chat.setIconBubleChat(R.drawable.icon_logo);
                Chat.setKeyFallBack("locna");
                Chat.setRequestMenuFAllback("fallback-locna");
                Chat.setSaleForceId("6");
                Chat.setEmail("ar_dev@gmail.com");
                Chat.setNameBot("BOT-Lenna");
                Chat.setNameAgent("SFTEST-QH");
//                Chat.setIconBot(R.drawable.icon_bot_room);
//                Chat.setIconAgent(R.drawable.icon_agent_room);
                Chat.setIconBitmap(iconLogo);
                Chat.setIconBubleChatBitmap(iconLogo);
                Chat.setIconBotBitmap(botRoom);
                Chat.setIconAgentBitmap(agentRoom);
                Chat.start(mContext);

                fragment = new ChatRoomLennaFragment();
                break;
            case 1:
                fragment = new NotifFragment();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}