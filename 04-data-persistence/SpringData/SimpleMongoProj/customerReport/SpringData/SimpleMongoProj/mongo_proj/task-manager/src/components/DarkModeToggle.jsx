import React, { useEffect, useState } from 'react';

const DarkModeToggle = () => {
    const [darkMode, setDarkMode] = useState(() => {
        return localStorage.getItem('theme') === 'dark';
    });

    useEffect(() => {
        const root = document.documentElement;
        if (darkMode) {
            root.classList.add('dark');
            localStorage.setItem('theme', 'dark');
        } else {
            root.classList.remove('dark');
            localStorage.setItem('theme', 'light');
        }
    }, [darkMode]);

    return (
        <div className="flex justify-end mb-4">
            <button
                onClick={() => setDarkMode(!darkMode)}
                className="px-4 py-2 rounded bg-gray-700 text-white"
            >
                {darkMode ? '☀️ Light Mode' : '🌙 Dark Mode'}
            </button>
        </div>
    );
};

export default DarkModeToggle;
