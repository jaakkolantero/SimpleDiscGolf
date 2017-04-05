package com.example.ufox.simplediscgolf;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CourseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseFragment extends Fragment {

    protected RecyclerView mCourseRecyclerView;
    protected CourseAdapter mCourseAdapter;
    protected ArrayList<CourseObject> mCourseObjectArrayList;

    private static final String TAG = "CourseRecyclerViewFragment";

    private OnFragmentInteractionListener mListener;

    public CourseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment CourseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseFragment newInstance() {
        CourseFragment fragment = new CourseFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataSet();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_course,container,false);
        rootView.setTag(TAG);

        mCourseRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_course);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mCourseRecyclerView.setLayoutManager(layoutManager);
        mCourseRecyclerView.setHasFixedSize(true);

        mCourseAdapter = new CourseAdapter(mCourseObjectArrayList);

        mCourseRecyclerView.setAdapter(mCourseAdapter);

        // Inflate the layout for this fragment
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    void initDataSet() {
        mCourseObjectArrayList = new ArrayList<CourseObject>();
        mCourseObjectArrayList.add(new CourseObject("Hoppu",new int[]{0,0,0} ));
        mCourseObjectArrayList.add(new CourseObject("Hoppu",new int[]{0,0,0} ));
        mCourseObjectArrayList.add(new CourseObject("Hoppu",new int[]{0,0,0} ));
        mCourseObjectArrayList.add(new CourseObject("Hoppu",new int[]{0,0,0} ));
        mCourseObjectArrayList.add(new CourseObject("Hoppu",new int[]{0,0,0} ));
        mCourseObjectArrayList.add(new CourseObject("Hoppu",new int[]{0,0,0} ));
        mCourseObjectArrayList.add(new CourseObject("Hoppu",new int[]{0,0,0} ));
        mCourseObjectArrayList.add(new CourseObject("Hoppu",new int[]{0,0,0} ));
    }
}
