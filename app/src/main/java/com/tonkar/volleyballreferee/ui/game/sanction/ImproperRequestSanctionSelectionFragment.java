package com.tonkar.volleyballreferee.ui.game.sanction;

import android.os.Bundle;
import android.view.*;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.tonkar.volleyballreferee.R;
import com.tonkar.volleyballreferee.engine.game.IGame;
import com.tonkar.volleyballreferee.engine.game.sanction.SanctionType;
import com.tonkar.volleyballreferee.engine.team.TeamType;

public class ImproperRequestSanctionSelectionFragment extends Fragment {

    private SanctionSelectionDialogFragment mSanctionSelectionDialogFragment;
    private IGame mGame;
    private TeamType mTeamType;
    private SanctionType mSelected;

    public static ImproperRequestSanctionSelectionFragment newInstance(TeamType teamType) {
        ImproperRequestSanctionSelectionFragment fragment = new ImproperRequestSanctionSelectionFragment();
        Bundle args = new Bundle();
        args.putString("teamType", teamType.toString());
        fragment.setArguments(args);
        return fragment;
    }

    void init(SanctionSelectionDialogFragment parent, IGame game) {
        mSanctionSelectionDialogFragment = parent;
        mGame = game;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.improper_request_sanction_selection, container, false);
        mTeamType = TeamType.valueOf(requireArguments().getString("teamType"));

        // For IR we keep it simple: team-level selection only, always a single option
        TextView title = view.findViewById(R.id.improper_request_title);
        if (title != null) title.setText(R.string.improper_request);

        mSelected = SanctionType.IMPROPER_REQUEST;
        if (mSanctionSelectionDialogFragment != null) {
            mSanctionSelectionDialogFragment.computeOkAvailability(R.id.improper_request_sanction_tab);
        }
        return view;
    }

    SanctionType getSelectedImproperRequest() {
        return mSelected;
    }
}
