package aws.example.controller;

import aws.example.dto.RegionAndAZ;
import com.amazonaws.util.EC2MetadataUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/aws")
public class RegionController {

    @GetMapping(path = "/region")
    public RegionAndAZ getRegionNameAndAZ() {
        RegionAndAZ regionAndAZ = new RegionAndAZ();
        regionAndAZ.setAz(EC2MetadataUtils.getAvailabilityZone());
        regionAndAZ.setRegionName(EC2MetadataUtils.getEC2InstanceRegion());
        return regionAndAZ;
    }
}