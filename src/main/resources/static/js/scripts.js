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
    location.reload();
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