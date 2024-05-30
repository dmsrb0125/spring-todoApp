// scripts.js
document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('create-form').addEventListener('submit', function(event) {
        event.preventDefault();
        const form = event.target;
        const title = form.title.value;
        const description = form.description.value;

        fetch('/api/todos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ title, description })
        })
            .then(response => {
                if (response.ok) {
                    alert('할 일이 등록되었습니다.');
                    location.reload();
                } else {
                    response.json().then(data => {
                        console.error('Error:', data);
                        alert('할 일 등록에 실패했습니다.');
                    }).catch(() => {
                        alert('할 일 등록에 실패했습니다.');
                    });
                }
            })
            .catch(error => {
                console.error('Fetch error:', error);
                alert('할 일 등록에 실패했습니다.');
            });
    });

    document.getElementById('edit-form').addEventListener('submit', function(event) {
        event.preventDefault();
        const form = event.target;
        const id = document.getElementById('edit-id').value;
        const title = document.getElementById('edit-title').value;
        const description = document.getElementById('edit-description').value;

        fetch(`/api/todos/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ title, description })
        })
            .then(response => {
                if (response.ok) {
                    alert('할 일이 수정되었습니다.');
                    location.reload();
                } else {
                    response.json().then(data => {
                        console.error('Error:', data);
                        alert('할 일 수정에 실패했습니다.');
                    }).catch(() => {
                        alert('할 일 수정에 실패했습니다.');
                    });
                }
            })
            .catch(error => {
                console.error('Fetch error:', error);
                alert('할 일 수정에 실패했습니다.');
            });
    });
});

function showDetail(element) {
    const title = element.querySelector('h4').innerText;
    const description = element.querySelector('.description').innerText;
    const createdAt = element.querySelector('p').innerText.split('작성일: ')[1];

    document.getElementById('detail-title').innerText = title;
    document.getElementById('detail-description').innerText = description;
    document.getElementById('detail-createdAt').innerText = '작성일: ' + createdAt;

    document.getElementById('edit-id').value = element.getAttribute('data-id');
    document.getElementById('edit-title').value = title;
    document.getElementById('edit-description').value = description;

    const detailSection = document.getElementById('detail-section');
    detailSection.style.display = 'block';
    detailSection.scrollIntoView({ behavior: 'smooth' });
}

function showEditForm() {
    document.getElementById('detail-section').style.display = 'none';
    document.getElementById('edit-section').style.display = 'block';
}

function deleteTodoFromDetail() {
    const id = document.getElementById('edit-id').value;

    if (confirm('정말로 삭제하시겠습니까?')) {
        fetch(`/api/todos/${id}`, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    alert('할 일이 삭제되었습니다.');
                    location.reload();
                } else {
                    alert('할 일 삭제에 실패했습니다.');
                }
            })
            .catch(error => {
                console.error('Fetch error:', error);
                alert('할 일 삭제에 실패했습니다.');
            });
    }
}

function deleteTodo(button, event) {
    event.stopPropagation();
    const todoItem = button.closest('.todo-item');
    const id = todoItem.getAttribute('data-id');

    if (confirm('정말로 삭제하시겠습니까?')) {
        fetch(`/api/todos/${id}`, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    alert('할 일이 삭제되었습니다.');
                    location.reload();
                } else {
                    alert('할 일 삭제에 실패했습니다.');
                }
            })
            .catch(error => {
                console.error('Fetch error:', error);
                alert('할 일 삭제에 실패했습니다.');
            });
    }
}
