
let loginBtn = document.getElementById("login")
let signInBtn = document.getElementById("signIn");
let usernameInput = document.getElementById("username");
let passwordInput = document.getElementById("password");


let isUsernameValid;
let isPasswordValid;
let isFieldsValid;

loginBtn.disabled = true;
signInBtn.disabled = true;


usernameInput.addEventListener("input", (e) => {
    if (usernameInput.value.length < 4) {
        isUsernameValid = false;    
    } 
    if (usernameInput.value.length >= 4)  {
        isUsernameValid = true;    
    }

    if (isUsernameValid == true && isPasswordValid == true) {
   
        loginBtn.disabled = false;    
        signInBtn.disabled = false;    

        loginBtn.classList.remove("disabled");     
        signInBtn.classList.remove("disabled");     
    
        isFieldsValid = true
    } else {
        loginBtn.disabled = true;       
        signInBtn.disabled = true;    
        
        loginBtn.classList.add("disabled");       
        signInBtn.classList.add("disabled");       
    
        isFieldsValid = false
    }    

})

passwordInput.addEventListener("input", (e) => {
    if (passwordInput.value.length < 8) {
        isPasswordValid = false;    
    } 
    if (passwordInput.value.length >= 8) {
        isPasswordValid = true;    
    }

    if (isUsernameValid == true && isPasswordValid == true) {
    
        loginBtn.disabled = false;
        signInBtn.disabled = false;

        loginBtn.classList.remove("disabled");
        signInBtn.classList.remove("disabled");

        isFieldsValid = true
    } else {
        loginBtn.disabled = true;
        signInBtn.disabled = true;

        loginBtn.classList.add("disabled");
        signInBtn.classList.add("disabled");

        isFieldsValid = false
    }

})