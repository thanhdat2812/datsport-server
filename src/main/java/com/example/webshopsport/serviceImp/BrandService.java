package com.example.webshopsport.serviceImp;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Sort;

import com.example.webshopsport.entities.Brand;
import com.example.webshopsport.model.BrandModel;
import com.example.webshopsport.repository.BrandRepository;
import com.example.webshopsport.service.IBrandService;

@Service
public class BrandService implements IBrandService {

	@Autowired
	BrandRepository brandRepository;

	@Override
	public BrandModel findBrandById(int id) {
		Brand brand = brandRepository.findBrandByBrandId(id);
		if (brand != null) {
			return convertToDTO(brand);
		}
		return null;
	}

	@Override
	public ArrayList<BrandModel> getAllBrandByStatus(int status) {
		ArrayList<Brand> listBrand;
		if (status == -1)
			listBrand = (ArrayList<Brand>) brandRepository.findAll(Sort.by(Sort.Direction.DESC, "brandId"));

		else
			listBrand = brandRepository.getAllBrandByStatus(status);
		ArrayList<BrandModel> listBrandModel = new ArrayList<>();
		for (Brand brand : listBrand) {
			listBrandModel.add(convertToDTO(brand));
		}
		return listBrandModel;
	}

	@Override
	public BrandModel createBrand(BrandModel brandModel) {
		Brand brand = convertToEntity(brandModel);
		Brand brand2 = brandRepository.findBrandByNameAndStatus(brand.getBrandName());
		if (brand2 != null) {
			return null;
		}
		brandRepository.save(brand);
		Brand result = brandRepository.findBrandByNameAndStatus(brand.getBrandName());
		return convertToDTO(result);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int updateBrand(BrandModel brandModel) {
		Brand checkBrand = brandRepository.findBrandByBrandId(brandModel.getBrand_id());
		if (checkBrand == null) {
			return 0;
		}
		Brand brand = convertToEntity(brandModel);
		brandRepository.updateBrandById(brand.getBrandName(), brand.getBrandStatus(), brand.getBrandId());

		return 1;
	}

	public Brand convertToEntity(BrandModel brandModel) {
		Brand brand = new Brand();
		brand.setBrandId(brandModel.getBrand_id());
		brand.setBrandName(brandModel.getBrand_name());
		brand.setBrandStatus(brandModel.getBrand_status());
		return brand;
	}

	public BrandModel convertToDTO(Brand brand) {
		BrandModel brandModel = new BrandModel();
		brandModel.setBrand_id(brand.getBrandId());
		brandModel.setBrand_name(brand.getBrandName());
		brandModel.setBrand_status(brand.getBrandStatus());
		return brandModel;
	}
}
