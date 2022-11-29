package com.auo.shelf.cmsapp.ui.shelf;

import android.media.Image;

import androidx.annotation.NonNull;
import androidx.camera.core.ExperimentalGetImage;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;

import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;

import java.util.List;

public class BarcodeAnalyzer implements ImageAnalysis.Analyzer{

    private final BarcodeScanner barcodeScanner = createBarcodeScanner();
    private final BarcodesListener barcodesListener;

    @FunctionalInterface
    public interface BarcodesListener {
        void invoke(List<Barcode> barcodes);
    }

    public BarcodeAnalyzer(BarcodesListener barcodesListener) {
        this.barcodesListener = barcodesListener;
    }

    @ExperimentalGetImage
    @Override
    public void analyze(@NonNull ImageProxy imageProxy) {
        Image image = imageProxy.getImage();
        if (image == null) {
            imageProxy.close();
            return;
        }

        int rotationDegrees = imageProxy.getImageInfo().getRotationDegrees();
        InputImage inputImage = InputImage.fromMediaImage(image, rotationDegrees);
        barcodeScanner.process(inputImage)
                .addOnSuccessListener(this::onGotBarcodes)
                .addOnCompleteListener(task -> imageProxy.close());
    }

    private void onGotBarcodes(List<Barcode> barcodes) {
        if (barcodes != null && !barcodes.isEmpty()) {
            barcodesListener.invoke(barcodes);
        }
    }

    private BarcodeScanner createBarcodeScanner() {
        BarcodeScannerOptions options = new BarcodeScannerOptions.Builder().setBarcodeFormats(Barcode.FORMAT_QR_CODE).build();
        return BarcodeScanning.getClient(options);
    }
}
