let selectedNoteId = null;

$(document).on('click', '.note-item', function () {
    const noteId = $(this).data('id');
    selectedNoteId = noteId;

    fetch(`/user/notes/${noteId}`)
        .then(response => response.json())
        .then(note => {
            $('#note-description').val(note.description);
            $('#edit-statuses').val(note.statuses);
            $('#save-note-btn, #delete-note-btn').show();
        })
        .catch(err => console.error('Error fetching note:', err));
});

$('#delete-note-btn').on('click', function () {
    if (!selectedNoteId) return;

    fetch(`/user/notes/${selectedNoteId}`,
        {
            method: 'DELETE' ,
            headers: {'X-CSRF-TOKEN': document.querySelector('input[name="_csrf"]').value}
        })
        .then(() => {
            $(`[data-id=${selectedNoteId}]`).remove();
            $('#note-description').val('').prop('readonly', true);
            $('#delete-note-btn').hide();
        })
        .catch(err => console.error('Error deleting note:', err));
});

$('#create-note-btn').on('click', function () {
    $('#create-note-modal').show();
});

$('#create-note-form').on('submit', function (event) {
    event.preventDefault();

    const newNote = {
        title: $('#title').val(),
        statuses: Array.from($('#statuses').val()),
        finishDate: $('#finishDate').val()
    };

    console.log('Creating new note:', newNote);

    fetch('/user/notes', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': document.querySelector('input[name="_csrf"]').value
        },
        body: JSON.stringify(newNote)
    })
        .then(response => response.json())
        .then(note => {
            $('#notes-list').append(`
            <li data-id="${note.id}" class="note-item">
                <strong>${note.title}</strong>
                <br>
                Statuses:
                <ul>
                    <li th:each="status : ${note.statusSet}">
                    <span th:text="${status.name}"></span>
                    </li>
                </ul>
                Finish Date: ${note.finishDate}
            </li>
        `);
            $('#create-note-modal').hide();
        })
        .catch(err => console.error('Error creating note:', err));
});

$('#save-note-btn').on('click', function () {
    if (!selectedNoteId) return;

    const updatedNote = {
        description: $('#note-description').val(),
        statuses: Array.from($('#edit-statuses').val())
    };

    console.log('Updating note:', updatedNote);

    fetch(`/user/notes/${selectedNoteId}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json',
            'X-CSRF-TOKEN': document.querySelector('input[name="_csrf"]').value
        },
        body: JSON.stringify(updatedNote)
    })
        .then(() => {
            console.log('Note updated successfully');
        })
        .catch(err => console.error('Error updating note:', err));
});
