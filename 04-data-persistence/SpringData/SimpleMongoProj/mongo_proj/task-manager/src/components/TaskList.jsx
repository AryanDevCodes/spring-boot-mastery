import React from 'react';

const TaskList = ({ tasks, setEditingTask, deleteTask }) => {
    return (
        <div className="space-y-4">
            {tasks.length === 0 ? (
                <p className="text-center text-gray-500">No tasks available.</p>
            ) : (
                tasks.map((task) => (
                    <div key={task.id} className="p-4 bg-white dark:bg-gray-800 rounded shadow">
                        <h2 className="text-xl font-bold">{task.title}</h2>
                        <p className="text-sm text-gray-500">{task.description}</p>
                        <div className="text-sm mt-1">
                            <span className="mr-4">Priority: {task.priority}</span>
                            <span>Due: {task.dueDate}</span>
                        </div>
                        <div className="mt-2 space-x-2">
                            <button
                                onClick={() => setEditingTask(task)}
                                className="bg-yellow-500 text-white px-3 py-1 rounded"
                            >
                                Edit
                            </button>
                            <button
                                onClick={() => deleteTask(task.id)}
                                className="bg-red-600 text-white px-3 py-1 rounded"
                            >
                                Delete
                            </button>
                        </div>
                    </div>
                ))
            )}
        </div>
    );
};

export default TaskList;
