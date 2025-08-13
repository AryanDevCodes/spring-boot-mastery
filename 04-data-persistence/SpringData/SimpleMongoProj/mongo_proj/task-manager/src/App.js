import React, { useEffect, useState } from 'react';
import axios from 'axios';
import TaskForm from './components/TaskForm';
import TaskList from './components/TaskList';
import DarkModeToggle from './components/DarkModeToggle';

function App() {
    const [tasks, setTasks] = useState([]);
    const [editingTask, setEditingTask] = useState(null);

    const fetchTasks = async () => {
        const res = await axios.get('http://localhost:8080/api/tasks');
        setTasks(res.data);
    };

    useEffect(() => {
        fetchTasks();
    }, []);

    const addTask = async (task) => {
        const res = await axios.post('http://localhost:8080/api/tasks', task);
        setTasks([...tasks, res.data]);
    };

    const updateTask = async (updatedTask) => {
        const res = await axios.put(`http://localhost:8080/api/tasks/${updatedTask.id}`, updatedTask);
        setTasks(tasks.map((task) => (task.id === updatedTask.id ? res.data : task)));
        setEditingTask(null);
    };

    const deleteTask = async (id) => {
        await axios.delete(`http://localhost:8080/api/tasks/${id}`);
        setTasks(tasks.filter((task) => task.id !== id));
    };

    return (
        <div className="min-h-screen bg-gray-100 dark:bg-gray-900 text-gray-900 dark:text-gray-100 p-6">
            <div className="max-w-4xl mx-auto">
                <DarkModeToggle />
                <h1 className="text-3xl font-bold text-center mb-6">Task Management App</h1>
                <TaskForm addTask={addTask} editingTask={editingTask} updateTask={updateTask} />
                <TaskList tasks={tasks} setEditingTask={setEditingTask} deleteTask={deleteTask} />
            </div>
        </div>
    );
}

export default App;
