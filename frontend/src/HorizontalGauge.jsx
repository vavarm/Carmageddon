import React from 'react';

const HorizontalGauge = ({ fuel }) => {
    const gaugeStyle = {
        width: '200px',
        height: '20px',
        backgroundColor: '#f0f0f0',
        border: '1px solid #ccc',
        borderRadius: '10px',
        position: 'relative',
        overflow: 'hidden'
    };

    const progressStyle = {
        height: '100%',
        width: `${fuel}%`,
        backgroundColor: fuel > 20 ? '#4CAF50' : '#FF9800',
        transition: 'width 0.5s ease'
    };

    return (
        <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center', height: '100%' }}>
            <div style={gaugeStyle}>
                <div style={progressStyle}></div>
            </div>
            <p>Fuel Level: {fuel}%</p>
        </div>
    );
};

export default HorizontalGauge;
