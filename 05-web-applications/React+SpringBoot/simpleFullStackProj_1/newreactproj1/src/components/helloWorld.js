import React , {useState,useEffect} from "react";

function HelloWorld() {
    const [message,setMessage] = useState('');

    useEffect(
        ()=>{
            fetch('/api/hello')
                .then(response => response.text())
                .then(data => setMessage(data))
        },[]
    );
    return <div>
        {message}
    </div>
}
export default HelloWorld