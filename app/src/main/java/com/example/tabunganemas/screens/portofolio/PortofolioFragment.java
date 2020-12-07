package com.example.tabunganemas.screens.portofolio;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tabunganemas.R;
import com.example.tabunganemas.models.Profile;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import static com.example.tabunganemas.BR.viewModel;
import static android.app.Activity.RESULT_OK;

public class PortofolioFragment extends Fragment {

    public PortofolioFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dexter.withContext(requireContext())
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Toast.makeText(getContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Toast.makeText(getContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        Toast.makeText(getContext(), "Permission Rationale", Toast.LENGTH_SHORT).show();
                    }
                })
                .check();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portofolio, container, false);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_portofolio, container, false);
        PortofolioViewModelFactory portofolioViewModelFactory = new PortofolioViewModelFactory(requireActivity().getApplication());
        viewModel = new ViewModelProvider(this,portofolioViewModelFactory).get(PortofolioViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.imageEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Profile profile = viewModel.getProfileLiveData().getValue();
                assert profile != null;
                NavDirections action = PortofolioFragmentDirections.actionPorfolioFragmentToProfileFragment(profile);
                Navigation.findNavController(requireView()).navigate(action);
            }
        });

        binding.imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });

        viewModel.getProfileLiveData().observe(getViewLifecycleOwner(), new Observer<Profile>() {
            @Override
            public void onChanged(Profile profile) {
                viewModel.getProfileLiveData().observe(getViewLifecycleOwner(), new Observer<Profile>() {
                    @Override
                    public void onChanged(Profile profile) {
                        binding.imageProfile.setImageURI(Uri.parse(profile.getImage()));Profile profile = viewModel.getProfileLiveData().getValue();
                        assert profile != null;
                        NavDirections action = PortofolioFragmentDirections.actionPorfolioFragmentToProfileFragment(profile);
                        Navigation.findNavController(requireView()).navigate(action);
                    }
                });
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK )
        {
            try {
                assert data != null;
                Uri imageUri = data.getData();
                binding.imageProfile.setImageURI(imageUri);
            } catch (Exception e) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}