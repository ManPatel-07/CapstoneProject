package org.mm.aadhar_service.client;

import org.mm.aadhar_service.dao.HelperIdDao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", path = "/users")
public interface UserFeignClient 
{
	@PostMapping(path = "/saveAadharId")
	public ResponseEntity<?> createAadharId(@RequestBody HelperIdDao helperIdDao );
}
