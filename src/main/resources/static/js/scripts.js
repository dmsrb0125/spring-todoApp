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
});
