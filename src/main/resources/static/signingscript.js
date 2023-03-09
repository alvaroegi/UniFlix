function changetosignup() {
    let content1 = document.getElementById('divlogin');
    let content2 = document.getElementById('divsignup');
    content1.style.display = 'none';
    content2.style.display = 'block';
}

function changetologin() {
    let content1 = document.getElementById('divlogin');
    let content2 = document.getElementById('divsignup');
    content1.style.display = 'block';
    content2.style.display = 'none';
}