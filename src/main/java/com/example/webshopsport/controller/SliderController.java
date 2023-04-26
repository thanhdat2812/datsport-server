package com.example.webshopsport.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webshopsport.model.ResponseObject;
import com.example.webshopsport.model.SliderModel;
import com.example.webshopsport.service.ISliderService;

@RestController
@CrossOrigin
public class SliderController {
	
	@Autowired
	ISliderService sliderService;
	
	@GetMapping("/api/getallslider/{status}")
	public ResponseEntity<ResponseObject> getAllSlider(@PathVariable("status") int status) {
		ArrayList<SliderModel> listSlider = sliderService.getAllSliderByStatus(status);
		if(listSlider != null) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject(200, "Get List Slider Success", listSlider));
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject(200, "Don't Slider To Get", null));
	}
	
	@GetMapping("/api/slider/{id}")
	public ResponseEntity<ResponseObject> getSliderById(@PathVariable("id") int id) {
		SliderModel slide=sliderService.getById(id);
		if(slide != null) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject(200, "Get List Slider Success", slide));
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject(200, "Not found", null));
	}
	
	@PostMapping("/admin/createslider")
	public ResponseEntity<ResponseObject> createSlider(@RequestBody SliderModel sliderModel) {
		SliderModel slider = sliderService.createSlider(sliderModel);
		if(slider != null) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject(200, "Create Slider Success", slider));
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject(200, "Create Slider Failed, Slider Is Exists", slider));
	}
	
	@PostMapping("/admin/updateslider")
	public ResponseEntity<ResponseObject> updateSlider(@RequestBody SliderModel sliderModel) {
		SliderModel slider = sliderService.updateSlider(sliderModel);
		if(slider != null) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject(200, "update Slider Success", slider));
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject(200, "Update Slider Failed", slider));
	}

}
