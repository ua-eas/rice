/**
 * Copyright 2005-2017 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.rice.kew.impl.stuck;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.core.api.config.property.RuntimeConfig;
import org.kuali.rice.core.api.mail.MailMessage;
import org.kuali.rice.core.api.mail.Mailer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import java.text.MessageFormat;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

public class StuckDocumentNotifierImpl implements StuckDocumentNotifier {

    private static final Logger LOG = LoggerFactory.getLogger(StuckDocumentNotifierImpl.class);

    private static final String EMAIL_TEMPLATE =
            "{0} stuck documents have been identified within the workflow system with the following document IDs:\n\n{1}";
    private static final String FAILURE_EMAIL_TEMPLATE =
            "Failed to autofix document {0}.\n\nIncident details:\n\tStarted: {1}\n\tEnded: {2}\n\nAttempts occurred at the following times:{3}";
    private static final String FAILURE_EMAIL_SUBJECT_TEMPLATE = "Failed to autofix stuck document with ID {0}";

    private RuntimeConfig from;
    private RuntimeConfig to;
    private RuntimeConfig subject;

    private Mailer mailer;

    @Override
    public void notify(List<String> documentIds) {
        if (!documentIds.isEmpty()) {
            send(MessageFormat.format(EMAIL_TEMPLATE, documentIds.size(), String.join("\n", documentIds)));
        }
    }

    @Override
    public void notifyIncidentFailure(StuckDocumentIncident incident, List<StuckDocumentFixAttempt> attempts) {
        send(MessageFormat.format(FAILURE_EMAIL_TEMPLATE, incident.getDocumentId(),
                incident.getStartDate().toString(),
                incident.getEndDate().toString(),
                generateAttemptsText(attempts)),
                MessageFormat.format(FAILURE_EMAIL_SUBJECT_TEMPLATE, incident.getDocumentId()));
    }

    private String generateAttemptsText(List<StuckDocumentFixAttempt> attempts) {
        StringBuilder builder = new StringBuilder();
        for (StuckDocumentFixAttempt attempt : attempts) {
            builder.append("\n\t").append(attempt.getTimestamp().toString());
        }
        return builder.toString();
    }

    private void send(String messageBody) {
        send(messageBody, subject.getValue());
    }

    private void send(String messageBody, String subject) {
        if (checkCanSend()) {
            MailMessage message = new MailMessage();
            message.setFromAddress(from.getValue());
            message.setToAddresses(Collections.singleton(to.getValue()));
            message.setSubject(subject);
            message.setMessage(messageBody);
            try {
                mailer.sendEmail(message);
            } catch (Exception e) {
                // we don't want some email configuration issue to mess up our stuck document processing, just log the error
                LOG.error("Failed to send stuck document notification email with the body:\n" + messageBody, e);
            }
        }
    }

    private boolean checkCanSend() {
        boolean canSend = true;
        if (StringUtils.isBlank(from.getValue())) {
            LOG.error("Cannot send stuck documentation notification because no 'from' address is configured.");
            canSend = false;
        }
        if (StringUtils.isBlank(to.getValue())) {
            LOG.error("Cannot send stuck documentation notification because no 'to' address is configured.");
            canSend = false;
        }
        return canSend;
    }

    @Required
    public void setFrom(RuntimeConfig from) {
        this.from = from;
    }

    @Required
    public void setTo(RuntimeConfig to) {
        this.to = to;
    }

    @Required
    public void setSubject(RuntimeConfig subject) {
        this.subject = subject;
    }

    @Required
    public void setMailer(Mailer mailer) {
        this.mailer = mailer;
    }

}
