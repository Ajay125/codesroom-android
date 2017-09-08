package firstapplication.codesroom.com.firstprojectfromcodesroom.fregments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import firstapplication.codesroom.com.firstprojectfromcodesroom.R;
import firstapplication.codesroom.com.firstprojectfromcodesroom.beans.MyItem;
import firstapplication.codesroom.com.firstprojectfromcodesroom.fregments.ListFragment.OnListFragmentInteractionListener;

/**
 * {@link RecyclerView.Adapter} that can display a {@link MyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ListRecyclerViewAdapter extends RecyclerView.Adapter<ListRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<MyItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public ListRecyclerViewAdapter(ArrayList<MyItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final MyItem myItem = mValues.get(position);

        holder.name.setText(myItem.getName());
        holder.description.setText(myItem.getOwn());


        if (myItem.getUrl() != null && !myItem.getUrl().isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .load(myItem.getThum())
                    .into(holder.thumb);
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(myItem);
                }
            }
        });
    }


    public void addItems(ArrayList<MyItem> myItems) {
        mValues.addAll(myItems);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView name;
        public final TextView description;
        private final ImageView thumb;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            name = (TextView) view.findViewById(R.id.textViewName);
            thumb = (ImageView) view.findViewById(R.id.imageViewThumb);
            description = (TextView) view.findViewById(R.id.textViewDescription);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + description.getText() + "'";
        }
    }
}
