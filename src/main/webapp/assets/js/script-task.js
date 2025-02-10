let btn_rem = document.getElementById("btn-remove-form");
let btn_plus = document.getElementById("btn-plus");
let form_background = document.getElementById("form-background");
let submit_button = document.getElementById("submit-button");


btn_plus.addEventListener('click', () => {
   form_background.classList.remove("invisible");
});

btn_rem.addEventListener('click', () => {
   form_background.classList.add("invisible");
});

btn_plus.addEventListener('click', () => {
   form_background.classList.remove("invisible");
});

submit_button.addEventListener('click', () => {
   setTimeout(()=>{
      form_background.classList.add("invisible");
   },100)
});