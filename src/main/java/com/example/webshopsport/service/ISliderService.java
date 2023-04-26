package com.example.webshopsport.service;

import java.util.ArrayList;

import com.example.webshopsport.model.SliderModel;

public interface ISliderService {
	
	public ArrayList<SliderModel> getAllSliderByStatus(int status);
	public SliderModel createSlider(SliderModel sliderModel);
	public SliderModel updateSlider(SliderModel sliderModel);
	public SliderModel getById(int id);
}
