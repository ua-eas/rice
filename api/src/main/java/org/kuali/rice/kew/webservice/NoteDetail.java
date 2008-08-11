package org.kuali.rice.kew.webservice;

/**
 * Response object encapsulating a note on a document
 */
public class NoteDetail {
    public String author;
    public String id;
    public String timestamp;
    public String noteText;
    
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public String getNoteText() {
        return noteText;
    }
    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }   
}
 