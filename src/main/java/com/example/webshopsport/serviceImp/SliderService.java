package com.example.webshopsport.serviceImp;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.webshopsport.entities.Slider;
import com.example.webshopsport.model.SliderModel;
import com.example.webshopsport.repository.SliderRepository;
import com.example.webshopsport.service.ISliderService;

@Service
public class SliderService implements ISliderService {

	@Autowired
	SliderRepository sliderRepository;

	@Override
	public ArrayList<SliderModel> getAllSliderByStatus(int status) {
		ArrayList<SliderModel> listSliderModel = new ArrayList<>();
		ArrayList<Slider> listSlider = null;
		if (status == -1) {
			listSlider = (ArrayList<Slider>) sliderRepository.findAll();
		} else {
			listSlider = (ArrayList<Slider>) sliderRepository.getAllSliderByStatus(status);
		}
		for (Slider slider : listSlider) {
			listSliderModel.add(convertToDTO(slider));
		}
		return listSliderModel;
	}

	@Override
	public SliderModel getById(int id) {
		Optional<Slider> result = sliderRepository.findById(id);
		return result.isPresent() ? convertToDTO(result.get()) : null;
	}

	@Override
	public SliderModel createSlider(SliderModel sliderModel) {
		Slider checkSlider = sliderRepository.findSliderByName(sliderModel.getSlider_name());
		if (checkSlider != null) {
			return null;
		}
		Slider slider = sliderRepository.save(convertToEntity(sliderModel));
		return convertToDTO(slider);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public SliderModel updateSlider(SliderModel sliderModel) {
		Slider checkSlider = sliderRepository.findSliderBySliderId(sliderModel.getSlider_id());
		if (checkSlider != null) {
			Slider slider = convertToEntity(sliderModel);
			slider.setSliderId(checkSlider.getSliderId());
			sliderRepository.save(slider);
			return convertToDTO(slider);
		}
		return null;
	}

	public Slider convertToEntity(SliderModel sliderModel) {
		Slider slider = new Slider();
		slider.setSliderId(sliderModel.getSlider_id());
		slider.setSliderName(sliderModel.getSlider_name());
		slider.setSliderImage(sliderModel.getSlider_image());
		slider.setSliderStatus(sliderModel.getSlider_status());
		return slider;
	}

	public SliderModel convertToDTO(Slider slider) {
		SliderModel sliderModel = new SliderModel();
		sliderModel.setSlider_id(slider.getSliderId());
		sliderModel.setSlider_name(slider.getSliderName());
		sliderModel.setSlider_image(slider.getSliderImage());
		sliderModel.setSlider_status(slider.getSliderStatus());
		return sliderModel;
	}

}
