function openModal(fileId) {
    var modal = document.getElementById("passwordModal");
    var form = modal.querySelector("form");
    form.action = "/download/" + fileId; // 다운로드 URL 설정
    modal.dataset.fileId = fileId;
    modal.style.display = "block";
}

function closeModal() {
    var modal = document.getElementById("passwordModal");
    modal.style.display = "none";
}

function checkPassword() {
    var password = document.getElementById("filePassword").value;
	var modal = document.getElementById("passwordModal");
    var fileId = modal.dataset.fileId; // 모달에서 fileId를 가져옴
    // 폼 제출을 막음
    event.preventDefault();

    // 비밀번호 확인 Ajax 요청
    $.ajax({
        type: "POST",
        url: "/checkPassword",
        data: JSON.stringify({ 
            id: fileId,
            password: password
        }),
        contentType: "application/json", // JSON 데이터를 보낼 때 필요
        asyce: false,
        success: function () {
            // 비밀번호가 올바를 때 모달 창을 닫고 다운로드 폼 제출
            closeModal();
            document.getElementById("passwordForm").submit();
        },
        error: function (xhr) {
            var errorData = JSON.parse(xhr.responseText);
		    alert(errorData.errorMessage);
        }
    });
}

function uploadFile() {
    var fileInput = document.getElementById("file");
    var progressBar = document.getElementById("progress-bar");

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/upload", true);

    xhr.upload.onprogress = function (event) {
        if (event.lengthComputable) {
            var percentComplete = (event.loaded / event.total) * 100;
            progressBar.style.width = percentComplete + "%";
        }
    };

    xhr.onload = function () {
        // 파일 업로드 완료 후 서버에서의 응답을 처리
        if (xhr.status === 200) {
            // 파일 업로드 성공
            console.log("파일 업로드 성공");
        } else {
            // 오류 처리
            console.error("파일 업로드 실패");
        }
    };

    var formData = new FormData();
    formData.append("file", fileInput.files[0]);
    // 필요한 경우 다른 폼 데이터를 추가합니다.

    xhr.send(formData);

    return false; // 폼이 일반적으로 제출되는 것을 방지합니다.
}