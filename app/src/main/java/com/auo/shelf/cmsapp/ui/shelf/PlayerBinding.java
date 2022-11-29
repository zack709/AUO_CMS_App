package com.auo.shelf.cmsapp.ui.shelf;

import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraControl;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.MeteringPoint;
import androidx.camera.core.MeteringPointFactory;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import com.auo.shelf.cmsapp.databinding.FragmentShelfPlayerBindingBinding;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.vision.barcode.common.Barcode;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PlayerBinding extends Fragment {

    private FragmentShelfPlayerBindingBinding binding;
    private ShelfViewModel shelfViewModel;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;

    private final ExecutorService cameraExecutor = Executors.newSingleThreadExecutor();
    private ProcessCameraProvider cameraProvider;
    private Camera camera;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shelfViewModel = new ViewModelProvider(getActivity()).get(ShelfViewModel.class);

        binding = FragmentShelfPlayerBindingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        cameraProviderFuture = ProcessCameraProvider.getInstance(this.getContext());
//        cameraProviderFuture.addListener(() -> {
//            try {
//                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
//                bindImageAnalysis(cameraProvider);
//            } catch (ExecutionException | InterruptedException e) {
//                e.printStackTrace();
//            }
//        }, ContextCompat.getMainExecutor(this.getContext()));

        startCamera();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        cameraExecutor.shutdown();
    }

//    private void bindImageAnalysis(@NonNull ProcessCameraProvider cameraProvider) {
//        ImageAnalysis imageAnalysis =
//                new ImageAnalysis.Builder().setTargetResolution(new Size(1280, 720))
//                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST).build();
//        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(this.getContext()), image -> image.close());
//        OrientationEventListener orientationEventListener = new OrientationEventListener(this.getContext()) {
//            @Override
//            public void onOrientationChanged(int orientation) {
////                textView.setText(Integer.toString(orientation));
//            }
//        };
//        orientationEventListener.enable();
//        Preview preview = new Preview.Builder().build();
//        CameraSelector cameraSelector = new CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build();
//        preview.setSurfaceProvider(binding.previewView.getSurfaceProvider());
//        cameraProvider.unbindAll();
//        cameraProvider.bindToLifecycle((LifecycleOwner)this, cameraSelector, imageAnalysis, preview);
//    }

    private void startCamera() {
        Executor mainExecutor = ContextCompat.getMainExecutor(this.getContext());
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this.getContext());
        cameraProviderFuture.addListener(() -> onGotCamera(cameraProviderFuture), mainExecutor);
    }

    @MainThread
    private void onGotCamera(@NonNull ListenableFuture<ProcessCameraProvider> cameraProviderFuture) {
        Preview previewUseCase = new Preview.Builder().build();
        previewUseCase.setSurfaceProvider(binding.previewView.getSurfaceProvider());

        ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build();

        BarcodeAnalyzer barcodeAnalyzer = new BarcodeAnalyzer(this::onGotBarcodes);
        imageAnalysis.setAnalyzer(cameraExecutor, barcodeAnalyzer);

        try {
            cameraProvider = cameraProviderFuture.get();
            cameraProvider.unbindAll();
            CameraSelector cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;
            camera = cameraProvider.bindToLifecycle(this, cameraSelector, previewUseCase, imageAnalysis);
            setupTapToFocus();
        }
        catch (ExecutionException | InterruptedException e) {
            setResultCanceledAndFinish();
        }
    }

    @MainThread
    private void onGotBarcodes(@NonNull List<Barcode> barcodes) {
        cameraProvider.unbindAll();     // we don't want any more barcodes
        String scannedQRCodeText = barcodes.get(0).getDisplayValue();
        setResultOkAndFinish(scannedQRCodeText);
    }

    private void setResultOkAndFinish(@Nullable String scannedQRCodeText) {
        Log.d("Zack", "QRCODE: " + scannedQRCodeText);
    }

    private void setResultCanceledAndFinish() {

    }

    private void setupTapToFocus() {
        binding.previewView.setOnTouchListener(this::onTouch);
    }

    private boolean onTouch(@NonNull View view, @NonNull MotionEvent event) {
        int actionMasked = event.getActionMasked();

        if (actionMasked == MotionEvent.ACTION_UP) {
            view.performClick();
            return false;
        }

        if (actionMasked != MotionEvent.ACTION_DOWN) {
            return false;
        }

        focus(event.getX(), event.getY());
        return true;
    }

    private void focus(float focusPointX, float focusPointY) {
        CameraControl cameraControl = camera.getCameraControl();

        MeteringPointFactory factory = binding.previewView.getMeteringPointFactory();
        MeteringPoint point = factory.createPoint(focusPointX, focusPointY);
        FocusMeteringAction action = new FocusMeteringAction.Builder(point, FocusMeteringAction.FLAG_AF)
                .addPoint(point, FocusMeteringAction.FLAG_AE)
                .addPoint(point, FocusMeteringAction.FLAG_AWB)
                .build();

        cameraControl.startFocusAndMetering(action);
    }


}