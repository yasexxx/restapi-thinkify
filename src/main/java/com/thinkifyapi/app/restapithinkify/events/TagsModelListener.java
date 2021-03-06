package com.thinkifyapi.app.restapithinkify.events;

import com.thinkifyapi.app.restapithinkify.models.Tags;
import com.thinkifyapi.app.restapithinkify.services.SequenceGeneratorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class TagsModelListener extends AbstractMongoEventListener<Tags>{
    
    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public void TagModelListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Tags> event) {
        if (event.getSource().getTagId() < 1) {
            event.getSource().setTagId(sequenceGenerator.generateSequence(Tags.SEQUENCE_NAME));
        }
    }
}
