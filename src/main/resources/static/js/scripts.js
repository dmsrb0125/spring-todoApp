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
});

function showDetail(element) {
    const title = element.querySelector('h4').innerText;
    const description = element.querySelector('.description').innerText;
    const createdAt = element.querySelector('p').innerText.split('작성일: ')[1];

    document.getElementById('detail-title').innerText = title;
    document.getElementById('detail-description').innerText = description;
    document.getElementById('detail-createdAt').innerText = '작성일: ' + createdAt;

    const detailSection = document.getElementById('detail-section');
    detailSection.style.display = 'block';
    detailSection.scrollIntoView({ behavior: 'smooth' });
}
