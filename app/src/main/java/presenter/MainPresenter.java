package presenter;

/**
 * Created by Administrator on 2017/12/26.
 */

public interface MainPresenter extends  BasePresenter {
    void getPlaceAndWeatherData(String 北京);

    void onRefresh();
}
