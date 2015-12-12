package org.cuacfm.contests.api.rest;

import javax.inject.Inject;

import org.cuacfm.contests.api.service.ContestService;
import org.cuacfm.contests.api.service.custom.BulkDataJSON;
import org.cuacfm.contests.api.service.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/load")
public class LoadingResource {

	@Inject
	private ContestService contestService;

	@ApiOperation(nickname = "Bulk loading data", value = "Bulk loading data")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> bulkLoad(@RequestBody BulkDataJSON data) throws NotFoundException {
		contestService.bulkLoad(data);
		return new ResponseEntity<>(HttpStatus.OK);

	}
}
