const save = document.getElementById("save");
const edit = document.getElementById("edit");
const a = document.getElementById("0x001");
const main = document.getElementById("main");
const Parent = document.getElementById("parent");
const title = document.getElementById('title');

console.log(save);
newDiv = document.createElement("div");
const htmlData = "";
var ID = 0;

var loadFile = function(event) {

	var img = document.getElementById(event.target.id).nextElementSibling.nextElementSibling;
	img.src = URL.createObjectURL(event.target.files[0]);
};

function add(s, event){
	text = ["<h1></h1>", "<p></p>", "<i></i>", "<br>", "<strong></strong>", "<blockquote></blockquote>", "<code></code>"];
	
	if(s == "Text"){
		const c = document.createElement("div");
		c.className = "a-text";
		c.id = "text"+ID++; // generate an ID for text field so that it matches with the .closeText id hence remove will be easy
		c.innerHTML = "<div class='bar'><h3 style='float: left; padding-top:10px;margin-left: 10px;'>Add Text</h3><span class='closeText' onclick='del(event)' style='float:right; margin-right: 10px;padding-top:10px;'>X</span></div><div class='feat'><div class='bar-2' id='bar-2'><span class='ele' id='h1' onclick='addIntoTextArea(text[0], event)'>h1</span><span onclick='addIntoTextArea(text[1], event)' class='ele'>P</span><span onclick='addIntoTextArea(text[2], event)' class='ele'>I</span><span onclick='addIntoTextArea(text[3], event)' class='ele'>Br</span><span onclick='addIntoTextArea(text[4], event)' class='ele'>Strong</span><span id='error' class='ele' style='display:none; color:red'>plaease insert h1 or p</span></div><div><textarea form='articlePost' name='text' class='tarea'></textarea></div></div>";
		arr = c.getElementsByClassName("ele");
		
		for (const aa in arr) {
			ids = "elem"+ID++;
			if (arr.hasOwnProperty(aa)) {
				const element = arr[aa];
				element.id = ids;
			}
		}
		c.getElementsByTagName("span")[0].id = ID-1;
		c.getElementsByTagName("textarea")[0].id = "textarea"+(ID-1);
		a.appendChild(c);
	}if(s == "Image"){
		img = "image";
		const c = document.createElement("div");
		c.className = "a-Image";
		c.id = "image"+ID++; // generate an ID for text field so that it matches with the .closeText id hence remove will be easy
		c.innerHTML = "<div class='bar'><h3 style='float: left; padding-top:10px;margin-left: 10px;'>Add Image</h3><span class='closeText' onclick='del(event)' style='float:right; margin-right: 10px;padding-top:10px;'>X</span></div><div class='feat'><div class='Image'><input type='file'  accept='image/*' name='image' id='file"+ID+"' onchange='loadFile(event)' form='articlePost' style='display: none;'><label for='file"+ID+"' class='selectImage' style='cursor: pointer; padding: 20px 0;'>Upload Image</label><img width='200' /> </div></div>";
		c.getElementsByTagName("span")[0].id = ID-1;//giving span an id
		c.getElementsByTagName('img')[0].id = ID-1; // giving the img a unique id\
		a.appendChild(c);
	}if(s == "block"){
		const c = document.createElement("div");
		c.className = "a-block";
		c.id = "block"+ID++; // generate an ID for text field so that it matches with the .closeText id hence remove will be easy
		c.innerHTML = "<div class='bar'><h3 style='float: left; padding-top:10px;margin-left: 10px;'>Add BloackQuote</h3><span class='closeText' onclick='del(event)' style='float:right; margin-right: 10px;padding-top:10px;'>X</span></div><div class='feat'><div class='bar-2'><span onclick='addIntoTextArea(text[5],event)' class='ele'>blockquote > </span></div><div><textarea form='articlePost' name='block' class='Barea'></textarea></div></div>";
		c.getElementsByTagName("span")[0].id = ID-1;
		c.getElementsByTagName("textarea")[0].id = "blockarea"+(ID-1);
		arr = c.getElementsByClassName("ele");
		for (const aa in arr) {
			ids = "elem"+ID++;
			if (arr.hasOwnProperty(aa)) {
				const element = arr[aa];
				element.id = ids;
			}
		}
		a.appendChild(c);
	}
	// <div class='bar'><h3 style='float: left; padding-top:10px;margin-left: 10px;'>Text</h3><span id='closeText' onclick='delT(this.id)' style='float:right; margin-right: 10px;padding-top:10px;'>X</span></div><div class='feat'><div class='bar-2'><span class='ele'>Code /></span></div><div><textarea class='Carea'></textarea></div></div>
	if(s == "code"){
		code = "<code></code>";
	
		const c = document.createElement("div");
	
		c.className = "a-code";
		c.id = "code"+ID++; // generate an ID for text field so that it matches with the .closeText id hence remove will be easy
		c.innerHTML = "<div class='bar'><h3 style='float: left; padding-top:10px;margin-left: 10px;'>Add Code</h3><span class='closeText' onclick='del(event)' style='float:right; margin-right: 10px;padding-top:10px;'>X</span></div><div class='feat'><div class='bar-2'><span onclick='addIntoTextArea(text[6], event)' class='ele'>Code /></span></div><div><textarea form='articlePost' name='code' class='Carea'></textarea></div></div>";
		c.getElementsByTagName("span")[0].id = ID-1;
		c.getElementsByTagName("textarea")[0].id = "codearea"+(ID-1);
		
		arr = c.getElementsByClassName("ele");
	
		for (const aa in arr) {
			ids = "elem"+ID++;
			if (arr.hasOwnProperty(aa)) {
				const element = arr[aa];
				element.id = ids;
			}
		}
		a.appendChild(c);
	}
}
function del(event){
	p = document.getElementById(event.target.id).parentNode.parentNode; // which is exactly the text or block node
	p.style.maxHeight = "0px";
	a.removeChild(p);
}
function addIntoTextArea(t, event){
	var error = document.getElementById(event.target.id).parentNode.lastChild;

	cursorPosVal = 0;

	var tarea = document.getElementById(event.target.id).parentElement.parentElement.parentElement.getElementsByTagName("textarea")[0];

	if(t.includes("strong") && tarea.value.length > 0 || t.includes("br") && tarea.value.length > 0){
		end = tarea.value.length;
		var startPos = tarea.selectionStart;
        var endPos = tarea.selectionEnd;	
        tarea.value = tarea.value.substring(0, startPos)
            + t
			+ tarea.value.substring(endPos, end);
		if(!t.includes("br")){
			tarea.selectionEnd = startPos+t.length/2;
		}else{
			tarea.selectionEnd = startPos+t.length;
		}
	}else if(tarea.value.length <= 0 && t.includes("strong") || t.includes("br")){
		error.style.display = "inline-block";
		error.style.fontSize = '15px';
		error.style.fontWeight = 'normal';
	}
	if(t.includes("h1")){
		error.style.display = "none";
		tarea.value += t+"\n";
		tarea.focus();
		tarea.selectionEnd = tarea.selectionEnd-t.length/2-1; // to specify how the cursor position shoild end
	}
	else if(t.includes("strong")){
		tarea.focus();
	}else if(t.includes("br")){
		tarea.focus();
		// tarea.value += t;
	}else if(t.includes("p")){
		error.style.display = "none";
		tarea.value += t+"\n";
		tarea.focus();
		tarea.selectionEnd = tarea.selectionEnd-t.length/2-1;
	}else if(t.includes("i")){
		error.style.display = "none";
		tarea.value += t+"\n";
		tarea.focus();
		tarea.selectionEnd = tarea.selectionEnd-t.length/2-1;
	}else if(t.includes("blockquote")){
		tarea.value = t;
		tarea.focus();
		tarea.selectionEnd = tarea.selectionEnd-t.length/2;
	}else if(t.includes("code")){
		tarea.value = t;
		tarea.focus();
		tarea.selectionEnd = tarea.selectionEnd-t.length/2;
	}
}
function Save(event){
	if(title.value == ''){
		title.style.border = '2px solid red';
		return;
	}

	var innerContent = "";
	
	var edit = document.getElementById("edit");
	var save = document.getElementById(event.target.id);

	save.style.display = "none";
	edit.style.display = "inline-block";
	
	var arr = new Array();
	
	arr = a.children;
	
	for (const key in arr) {
		if (arr.hasOwnProperty(key)) {
			const element = arr[key];
			if(element.id.includes("text")){
				Stext = element.getElementsByTagName("textarea")[0];
				innerContent+=Stext.value+"\n";
			}else if(element.id.includes("image")){
				image = element.getElementsByTagName("img")[0];
				innerContent +='<img id="9" width="200" src="'+image.src+'"></img>\n'
			}else if(element.id.includes("block")){
				Btext = element.getElementsByTagName("textarea")[0];
				innerContent += Btext.value+"\n";
			}else if(element.id.includes("code")){
				Ctext = element.getElementsByTagName("textarea")[0];
				innerContent += Ctext.value+"\n";
			}
		}
	}
	main.style.display = "none";
	newDiv = document.createElement("div");
	newDiv.className = "a-save";
	newDiv.style.display = 'block';
	newDiv.innerHTML += "<h1>Ttile</h1>"
	newDiv.innerHTML += document.getElementById('title').value+'<br>';
	newDiv.innerHTML += "<h1>Description</h1><br>"
	newDiv.innerHTML += document.getElementById('description').value+'\n';
	newDiv.innerHTML += innerContent;
	Parent.appendChild(newDiv);
}
function Edit(event){
	save.style.display = "inline-block";
	edit.style.display = "none";
	main.style.display = "flex";
	newDiv.style.display = "none";
}
inter = setInterval(function() {
	if(title.value.length > 0 && title.style.borderColor == 'red'){
		title.style.border = '1px solid black';
		clearInterval(inter);
		return;
	}
}, 500);