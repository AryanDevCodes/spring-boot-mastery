import React, { useState, useEffect } from 'react';

const TaskForm = ({ addTask, editingTask, updateTask }) => {
    const [formData, setFormData] = useState({
        title: '',
        description: '',
        dueDate: '',
        priority: 'Low',
    });

    useEffect(() => {
        if (editingTask) {
            setFormData(editingTask);
        }
    }, [editingTask]);

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        editingTask ? updateTask(formData) : addTask(formData);
        setFormData({ title: '', description: '', dueDate: '', priority: 'Low' });
    };

    return (
        <form onSubmit={handleSubmit} className="mb-6 p-4 bg-white dark:bg-gray-800 rounded shadow">
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <input
                    name="title"
                    value={formData.title}
                    onChange={handleChange}
                    placeholder="Task Title"
                    required
                    className="p-2 border rounded"
                />
                <input
                    name="dueDate"
                    value={formData.dueDate}
                    onChange={handleChange}
                    type="date"
                    className="p-2 border rounded"
                />
                <input
                    name="description"
                    value={formData.description}
                    onChange={handleChange}
                    placeholder="Description"
                    className="p-2 border rounded col-span-2"
                />
                <select
                    name="priority"
                    value={formData.priority}
                    onChange={handleChange}
                    className="p-2 border rounded"
                >
                    <option>Low</option>
                    <option>Medium</option>
                    <option>High</option>
                </select>
                <button type="submit" className="bg-blue-600 text-white px-4 py-2 rounded">
                    {editingTask ? 'Update Task' : 'Add Task'}
                </button>
            </div>
        </form>
    );
};

export default TaskForm;
