package com.example.fitclub;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import androidx.appcompat.widget.Toolbar;


public class MainActivity_old extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_old);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarid);

        if (toolbar != null)
            setSupportActionBar(toolbar);

// Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.main_page_background)
                .addProfiles(
                        new ProfileDrawerItem().withName("Mike Penz").withTextColorRes(R.color.md_light_cards).withEmail("mikepenz@gmail.com").withIcon(getResources().getDrawable(R.drawable.ic_account_white_24dp))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                }).withSelectionListEnabledForSingleProfile(false)
                .build();

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Тренировки").withIcon(R.drawable.ic_gym_black_24dp);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Мои занятия").withIcon(R.drawable.ic_favorite_black_24dp);
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName("Новости и уведомления").withIcon(R.drawable.ic_news_black_24dp);
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("Личный кабинет").withIcon(R.drawable.ic_account_black_24_dp);

        DrawerBuilder builder = new DrawerBuilder().withActivity(this).withToolbar(toolbar).addDrawerItems(
                item1, new DividerDrawerItem(),item2,  new DividerDrawerItem(), item3,new DividerDrawerItem(),item4
        ).withAccountHeader(headerResult);

        Drawer drawer = builder.build();

    }
}
