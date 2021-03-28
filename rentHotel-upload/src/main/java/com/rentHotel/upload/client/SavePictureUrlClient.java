package com.rentHotel.upload.client;


import com.rentHotel.item.api.SavePictureApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("hotel-service")
public interface SavePictureUrlClient extends SavePictureApi {
}
