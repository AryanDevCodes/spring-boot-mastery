import React, { useState, useEffect } from "react";
import axios from "axios";

const TaskManager = () => {
  const [tasks, setTasks] = useState([]);
  const [taskInput, setTaskInput] = useState("");

  useEffect(() => {
    fetchTasks();
  }, []);

  const fetchTasks = async () => {
    const response = await axios.get("http://localhost:8080/api/tasks");
    setTasks(response.data);
  };

  const handleAddTask = async () => {
    if (taskInput.trim()) {
      const response = await axios.post("http://localhost:8080/api/tasks", {
        description: taskInput,
      });
      setTasks([...tasks, response.data]);
      setTaskInput("");
    }
  };

  const handleDeleteTask = async (id) => {
    await axios.delete(`http://localhost:8080/api/tasks/${id}`);
    setTasks(tasks.filter((task) => task.id !== id));
  };

  return (
    <div className="max-w-xl mx-auto bg-white p-6 rounded shadow-md">
      <div className="flex gap-2 mb-4">
        <input
          type="text"
          value={taskInput}
          onChange={(e) => setTaskInput(e.target.value)}
          className="flex-1 border border-gray-300 rounded px-3 py-2"
          placeholder="Enter a task"
        />
        <button
          onClick={handleAddTask}
          className="bg-blue-600 text-white px-4 py-2 rounded"
        >
          Add
        </button>
      </div>
      <ul className="space-y-2">
        {tasks.map((task) => (
          <li
            key={task.id}
            className="flex justify-between items-center border p-2 rounded"
          >
            {task.description}
            <button
              onClick={() => handleDeleteTask(task.id)}
              className="text-red-600 hover:underline"
            >
              Delete
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default TaskManager;