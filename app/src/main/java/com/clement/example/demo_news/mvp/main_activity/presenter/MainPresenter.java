package com.clement.example.demo_news.mvp.main_activity.presenter;

import android.view.MenuItem;

import com.clement.example.demo_news.R;
import com.clement.example.demo_news.mvp.main_activity.view.MainActivity;
import com.clement.example.demo_news.mvp.main_activity.view.MainView;
import com.clement.example.demo_news.navigation.wx_new.WxNewFragment;

/**MainActivity的presenter
 * Created by clement on 16/11/10.
 */

public class MainPresenter {
    //持有view对象
    private MainView mainView;

    /**构造函数内,绑定MainView
     * @param mainView
     */
    public MainPresenter(MainView mainView){
        this.mainView = mainView ;
    }

    /**item点击执行函数
     * @param item
     * @return
     */
    public boolean onNavigationItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.wx_new_fragment) {
            //跳转到fragment
            ((MainActivity)mainView).replaceFragment(new WxNewFragment(),R.id.fragment_container);
        } else if (id == R.id.new_fragment) {

        } else if (id == R.id.picture_fragment) {

        } else if (id == R.id.anecdote_fragment) {

        } else if (id == R.id.hot_spot_fragment) {

        }else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        //关闭drawer
        mainView.closeDrawer();
        return true;
    }

    /**OptionsItemSelected
     * @param item
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return false ;
    }
}
