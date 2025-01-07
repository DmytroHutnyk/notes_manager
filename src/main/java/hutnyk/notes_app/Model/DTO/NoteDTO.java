package hutnyk.notes_app.Model.DTO;

import java.util.Date;
import java.util.Set;

public class NoteDTO {

    private String title;
    private String description;
    private Date finishDate;
    private Long userId;
    private Set<Long> statusSetId;

    public NoteDTO() {
    }

    public NoteDTO(String title, String description, Date finishDate,  Long userId, Set<Long> statusSetId) {
        this.title = title;
        this.finishDate = finishDate;
        this.userId = userId;
        this.statusSetId = statusSetId;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<Long> getStatusSetId() {
        return statusSetId;
    }

    public void setStatusSetId(Set<Long> statusSetId) {
        this.statusSetId = statusSetId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "NoteDTO{" +
                "title='" + title + '\'' +
                "description='" + description + '\'' +
                ", finishDate=" + finishDate +
                ", userId=" + userId +
                ", statusSetId=" + statusSetId +
                '}';
    }
}
