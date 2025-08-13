import React, { useState } from 'react';

const TaskItem = ({ task, onDelete }) => {
    const [isRemoving, setIsRemoving] = useState(false);

    const handleDelete = () => {
        setIsRemoving(true);
        setTimeout(() => onDelete(task._id), 500); // wait for fadeOut to finish
    };

    return (
        <div
            className={`p-4 rounded shadow mb-2 transition-all bg-white dark:bg-gray-800 ${
                isRemoving ? 'animate-fadeOut' : 'animate-fadeIn'
            }`}
        >
            <div className="flex justify-between items-center">
                <div>
                    <h3 className="font-semibold text-lg">{task.title}</h3>
                    <p className="text-gray-600 dark:text-gray-300">{task.description}</p>
                </div>
                <button
                    onClick={handleDelete}
                    className="text-red-500 hover:text-red-700 transition"
                >
                    Delete
                </button>
            </div>
        </div>
    );
};

export default TaskItem;
