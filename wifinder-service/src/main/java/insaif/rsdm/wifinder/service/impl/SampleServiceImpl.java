package insaif.rsdm.wifinder.service.impl;

import insaif.rsdm.wifinder.model.sample.Sample;
import insaif.rsdm.wifinder.repository.sample.SampleRepository;
import insaif.rsdm.wifinder.service.SampleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService {

    private static final Logger log = LoggerFactory.getLogger(SampleServiceImpl.class);

    private SampleRepository sampleRepository;

    @Autowired
    public SampleServiceImpl(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    public Sample createSample(String name) {
        Sample sample = new Sample();
        sample.setName(name);

        log.debug("Creating new sample with name {}", name);

        //Sample created = sampleRepository.save(sample);

        //log.debug("Sample named {} have been created with id : {}", sample.getName(), created.getId());

        return sample;
    }
}
