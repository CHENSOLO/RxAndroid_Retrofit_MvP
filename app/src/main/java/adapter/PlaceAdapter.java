package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chensolo.rxandroid_retrofit_mvp.R;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import model.Place;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/12/26.
 */

public class PlaceAdapter extends BaseListAdapter<Place>{
    private OnPlaceClickListener mOnPlaceClickListener;

    public void setmOnPlaceClickListener(OnPlaceClickListener listener) {
        mOnPlaceClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place,parent,false);

        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        Place place = getItem(position);
        itemViewHolder.place = place;
         itemViewHolder.tvPlace.setText(place.getName());

    }

   public interface OnPlaceClickListener {
         void onClick(View view, Place place);
   }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_place)
        TextView tvPlace;
        public Place place;
        public ItemViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            RxView.clicks(itemView).throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
                @Override
                public void call(Void aVoid) {
                    mOnPlaceClickListener.onClick(itemView,place);
                }
            });
        }
    }
}
