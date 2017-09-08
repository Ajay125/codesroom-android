package firstapplication.codesroom.com.firstprojectfromcodesroom.fregments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import firstapplication.codesroom.com.firstprojectfromcodesroom.R;
import firstapplication.codesroom.com.firstprojectfromcodesroom.beans.MyItem;
import firstapplication.codesroom.com.firstprojectfromcodesroom.network.ApiConnector;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ListFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private ListRecyclerViewAdapter adapter;
    private TextView loadingTextView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ListFragment newInstance(int columnCount) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);

        loadingTextView = (TextView) view.findViewById(R.id.loadingTextView);


        Context context = view.getContext();
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }

        adapter = new ListRecyclerViewAdapter(new ArrayList<MyItem>(), mListener);
        recyclerView.setAdapter(adapter);
        loadDataFromApi();
        return view;
    }

    private void loadDataFromApi() {
        new AsyncTask<String, Void, JSONArray>() {
            @Override
            protected JSONArray doInBackground(String... params) {
                String s = ApiConnector.getItems();
                if (!s.isEmpty())
                    try {
                        return new JSONArray(s);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                return null;
            }

            @Override
            protected void onPostExecute(JSONArray jsonArray) {
                ArrayList<MyItem> myItems = new ArrayList<MyItem>();
                for (int index = 0; index < jsonArray.length(); index++) {
                    JSONObject object = jsonArray.optJSONObject(index);
                    if (object != null) {
                        MyItem myItem = new MyItem();
                        myItem.setId(object.optString("id"));
                        myItem.setName(object.optString("title"));
                        myItem.setDesc(object.optString("desc"));
                        myItem.setUrl(object.optString("url"));
                        myItem.setOwn(object.optString("own"));
                        myItem.setThum(object.optString("thumbnail"));
                        myItems.add(myItem);
                    }
                }
                onLoadComplete(myItems);
            }
        }.execute();

    }

    private void onLoadComplete(ArrayList<MyItem> myItems) {
        adapter.addItems(myItems);
        if (adapter.getItemCount() > 0) {
            loadingTextView.setVisibility(View.GONE);
        } else {
            loadingTextView.setVisibility(View.VISIBLE);
            loadingTextView.setText("(Empty)");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(MyItem item);
    }
}
