package cr.ac.itcr.examen1;

import android.content.Context;
import android.content.Intent;
import android.location.GpsStatus;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cr.ac.itcr.examen1.Adapter.AdapterBird;
import cr.ac.itcr.examen1.Class.Bird;
import cr.ac.itcr.examen1.Class.User;
import cr.ac.itcr.examen1.Data.BirdRepository;
import cr.ac.itcr.examen1.Data.IRepository;
import cr.ac.itcr.examen1.Data.UserRepository;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListViewBirdFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListViewBirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListViewBirdFragment extends Fragment{

    AdapterBird adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ListViewBirdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListViewBirdFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListViewBirdFragment newInstance(String param1, String param2) {
        ListViewBirdFragment fragment = new ListViewBirdFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        IRepository repository = new BirdRepository(getContext().getApplicationContext());
        ArrayList<Bird> listBird = repository.GetAll();

        View view = inflater.inflate(R.layout.fragment_list_view_bird, container, false);
        ListView lista = (ListView) view.findViewById(R.id.ContenlistView);

        adapter = new AdapterBird(getActivity().getApplicationContext(),listBird);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                TextView name = (TextView) view.findViewById(R.id.nameBirdView);
                TextView color = (TextView) view.findViewById(R.id.colorBirdView);
                TextView classification = (TextView) view.findViewById(R.id.classificationBirdView);
                TextView location = (TextView) view.findViewById(R.id.locationBirdView);
                TextView idBird = (TextView) view.findViewById(R.id.idBird);

                Intent i = new Intent(getContext().getApplicationContext(), EditDeleteActivity.class);
                i.putExtra("nameBird", name.getText().toString());
                i.putExtra("colorBird", color.getText().toString());
                i.putExtra("classificationBird", classification.getText().toString());
                i.putExtra("locationBird", location.getText().toString());
                i.putExtra("idBird", idBird.getText().toString());
                startActivity(i);
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        IRepository repository = new BirdRepository(getContext().getApplicationContext());
        ArrayList<Bird> listBird = repository.GetAll();
        ListView lista = (ListView) getActivity().findViewById(R.id.ContenlistView);

        adapter = new AdapterBird(getActivity().getApplicationContext(),listBird);
        lista.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    /*@Override
    public void onDestroyView() {
        super.onDestroyView();
        Fragment fragment = (getFragmentManager().findFragmentById(R.id.birdView_fragment));
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.remove(fragment);
        ft.commit();
    }*/
}
