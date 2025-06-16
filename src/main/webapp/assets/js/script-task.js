document.addEventListener("DOMContentLoaded", function () {
   let btn_rem = document.getElementById("btn-remove-form");
   let btn_rem_ETD = document.getElementById("btn-remove-form-ETD");
   let btn_plus = document.getElementById("btn-plus");
   let form_background = document.getElementById("form-background");
   let form_ETD_background = document.getElementById("ETD_background");
   let form_ETD = document.getElementById("form-EditTask")
   let taskID_inputValue = document.getElementById("taskID")
   let menu_icon = document.querySelectorAll(".menu-icon")
   let submit_button = document.getElementById("submit-button");
   let submit_button_ETD = document.getElementById("submit-button_ETD");
   let term_input = document.getElementById("term-input")
   let input_name = document.getElementById("input_name");
   let input_desc = document.getElementById("input_desc");
   let input_term = document.getElementById("input_term");
   let input_status = document.getElementById("input_status");
   let list = document.getElementById("list")
   let taskID;

   
   btn_plus.addEventListener('click', () => {
      form_background.classList.remove("invisible");
   });

   btn_rem.addEventListener('click', () => {
      form_background.classList.add("invisible");
   });

   btn_rem_ETD.addEventListener('click', () => {
      form_ETD_background.classList.add("invisible");
   });

   btn_plus.addEventListener('click', () => {
      form_background.classList.remove("invisible");
   });

   submit_button.addEventListener('click', () => {
      setTimeout(()=>{
         form_background.classList.add("invisible");
      },100)
   });
   
   submit_button_ETD.addEventListener('click', () => {
      setTimeout(()=>{
         form_ETD_background.classList.add("invisible");
      },100)
   });
   
   let today = new Date().toISOString().split("T")[0];
   term_input.value = today;


   menu_icon.forEach((btn) => {
      
      btn.addEventListener('click', () => {
         let task_div_container = btn.parentNode
         let TDC_ID = task_div_container.id
         taskID = TDC_ID;
         let name_txt;
         let desc_txt;
         let term_txt;
         let status_txt;

         taskData_texts = task_div_container.children
         
         name_txt = taskData_texts[1].textContent
         desc_txt = taskData_texts[2].textContent
         term_txt = taskData_texts[3].textContent
         
         taskID_inputValue.value =  "" + TDC_ID + "";
         form_ETD_background.classList.remove("invisible");

         /*
         let PRI_list = {
            _name : name_txt,
            _description : desc_txt,
            _term : term_txt
         }
         let dataList = JSON.stringify(PRI_list);
         list.value = dataList;
         */

         input_name.value = name_txt;
         input_desc.value = desc_txt;
         input_term.value = term_txt;

         console.log(term_txt)
         console.log(name_txt)
         console.log(description_txt)
      })
   })
});
//             
