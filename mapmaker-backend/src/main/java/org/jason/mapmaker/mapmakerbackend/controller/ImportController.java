package org.jason.mapmaker.mapmakerbackend.controller;

import org.jason.mapmaker.mapmakerbackend.enumerated.InputFormat;
import org.jason.mapmaker.mapmakerbackend.service.GeographyStrategy;
import org.jason.mapmaker.mapmakerbackend.service.GeographyStrategyFactory;
import org.jason.mapmaker.shared.jaxb.ImportRequest;
import org.jason.mapmaker.shared.jaxb.ImportResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller to allow the import of data in its original format
 *
 * @author jason
 */
@Controller
public class ImportController {

    @RequestMapping(value = "/upload", method= RequestMethod.POST)
    public @ResponseBody
    ImportResponse uploadShape(@RequestBody ImportRequest request) {

        GeographyStrategy strategy = GeographyStrategyFactory.create(InputFormat.valueOf(request.getInitialType()), request.getEncodedData());
        return null;
    }
}
