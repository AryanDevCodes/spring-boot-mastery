import {useEffect, useState} from "react";
import type {Student} from "./types.ts";
import axios from "axios";
import * as React from "react";

function App() {
    const [student, setStudent] = useState<Student[]>([]);
    const [form, setForm] = useState<Student>({name:"", email:""});
    const [editId, setEditId] = useState<number | null>(null)
    const api = "http://localhost:8080/api/students";

    useEffect(() => {
        axios.get<Student[]>(api).then((res)=> setStudent(res.data));
    }, []);


        const handleSubmit = async (e:React.FormEvent)=>{
            e.preventDefault()
            if (editId){
                await axios.put(`$\{api}/${editId}`, form);
            }else{
                await axios.post(api,form);
            }

            setForm({name:"", email:""});
            setEditId(null);

            const res = await axios.get<Student[]>(api);
            setStudent(res.data)
        };

    const handleDelete = async (id:number)=>{
        await axios.delete(`${api}/${id}`);
        setStudent(student.filter((s)=>s.id !==id));
    }

    const handleEdit= (student:Student)=>{
        setForm({name:student.name, email:student.email});
        setEditId(student.id || null);
    };

    return(

            <div className="p-6 max-w-xl mx-auto">
                <h1 className="text-2xl font-bold mb-4">Student Management </h1>

                <form onSubmit={handleSubmit}
                        className="space-y-2 mb-6">
                    <input type="text" name="" id="" placeholder="Name" value={form.name} className="border p-2 w-full"
                           onChange={(e)=> setForm({...form, name: e.target.value})}>
                    </input>
                    <input
                        type="email"
                        placeholder="Email"
                        value={form.email}
                        className="border p-2 w-full"
                        onChange={(e)=>setForm({...form,email:e.target.value})}
                        required
                    ></input>

                    <button type={"submit"} className="bg-blue-600 text-white px-4 py-2 rounded">
                        {editId?"Update":"Add"}
                    </button>
                </form>

                <ul className="space-y-2">
                    {student.map((s)=>(
                    <li key={s.id}
                        className="flex justify-between items-center border p-2 rounded"
                    >
                        <span>
                            {s.name} - {s.email}
                        </span>
                        <div className="space-x-2">
                            <button
                                onClick={()=>handleEdit(s)}
                                className="bg-yellow-500 text-white px-2 py-1 rounded">
                                Edit
                            </button>

                            <button
                                onClick={()=>handleDelete(s.id!)}
                                className="bg-red-600  text-white px-2 py-1 rounded">
                                Delete
                            </button>
                        </div>
                    </li>
                    ))}
                </ul>
            </div>

        )
}
export default App;