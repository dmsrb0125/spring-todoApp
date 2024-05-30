function showDetails(element) {
    const id = element.getAttribute('data-id');
    clearDetails(); // Clear previous details
    fetch("/memos/" + id)
        .then(response => response.json())
        .then(data => {
            document.getElementById("details-title").innerText = data.title;
            document.getElementById("details-date").innerText = "작성일: " + data.createdAt;
            document.getElementById("details-username").innerText = "작성자: " + data.username;
            document.getElementById("details-description").innerText = data.description;
            document.querySelector("#edit-form input[name='title']").value = data.title;
            document.querySelector("#edit-form textarea[name='description']").value = data.description;
            document.querySelector("#edit-form input[name='username']").value = data.username;
            document.getElementById("details-section").style.display = "block";
        });
}

function clearDetails() {
    document.getElementById("details-title").innerText = "할 일 제목";
    document.getElementById("details-date").innerText = "작성일: 날짜";
    document.getElementById("details-username").innerText = "작성자: 사용자 이름";
    document.getElementById("details-description").innerText = "할 일 내용";
    document.getElementById("details-section").style.display = "none";
}

function editMemo() {
    document.getElementById("edit-form").style.display = "block";
}

function updateMemo(event) {
    event.preventDefault();
    const form = event.target;
    const id = document.querySelector("#details-section").getAttribute('data-id');
    const title = form.title.value;
    const description = form.description.value;
    const username = form.username.value;

    fetch("/memos/" + id, {
        method: "PUT",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
        },
        body: new URLSearchParams({
            title: title,
            description: description,
            username: username
        })
    })
        .then(response => {
            if (response.ok) {
                alert("메모가 수정되었습니다.");
                location.reload();
            } else {
                alert("메모 수정에 실패했습니다.");
            }
        });
}

function deleteMemo() {
    const id = document.querySelector("#details-section").getAttribute('data-id');
    fetch("/memos/" + id, {
        method: "DELETE"
    })
        .then(response => {
            if (response.ok) {
                alert("메모가 삭제되었습니다.");
                location.reload();
            } else {
                alert("메모 삭제에 실패했습니다.");
            }
        });
}
