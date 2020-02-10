package vira.alemamah.SliderAdapter;

import android.content.Context;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.List;

public class SliderAdapter {
    private List<Integer> itemSlider;
    private SliderLayout sliderLayout;
    private Context context;

    public SliderAdapter(List<Integer> itemSlider, SliderLayout sliderLayout, Context context) {
        this.itemSlider = itemSlider;
        this.sliderLayout = sliderLayout;
        this.context = context;
    }

    public void setItemSlider(){
        for (int i=0; i<itemSlider.size();i++){
            TextSliderView textSliderView = new TextSliderView(context);
            textSliderView
                    .image(itemSlider.get(i))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setDuration(5000);
    }
}
