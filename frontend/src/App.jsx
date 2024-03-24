import {useEffect, useState} from 'react'
import axios from 'axios'

function App() {

    const backendUrl = import.meta.env.VITE_BACKEND_URL

    const delay = 10 //ms

    const [startTime, setStartTime] = useState(Date.now())

    const [pseudoField, setPseudoField] = useState('')

    const [pseudo, setPseudo] = useState('')

    const [game, setGame] = useState({x: 0, y: 0, garages: [], gasStations: [], vehicles: []})
    // game with x, y, garages, gasStations, vehicles
    /* const [game, setGame] = useState(
        {
            x: 10,
            y: 10,
            garages: [{x: 3, y: 6}, {x: 7, y: 7}],
            gasStations: [{x: 1, y: 1}, {x: 9, y: 9}],
            vehicles: [
                {x: 3, y: 6, pseudo: 'toto', direction: "UP"},
                {x: 3, y: 6, pseudo: 'tata', direction: "DOWN"},
                {x: 3, y: 9, pseudo: 'titi', direction: "LEFT"},
                {x: 1, y: 1, pseudo: 'tutu', direction: "RIGHT"},
                {x: 9, y: 7, pseudo: 'tete', direction: "UP"},
                {x: 9, y: 2, pseudo: 'tyty', direction: "DOWN"}
            ],
        })
     */

    const createGame = async () => {
        try {
            const response = await axios.post(`${backendUrl}/game`, {
                x: 10,
                y: 10,
            })
            console.log(response.data)
            setGame(prevState =>
                ({...prevState, x: response.data.x, y: response.data.y, garages: [], gasStations: [], vehicles: []})
            )
        } catch (error) {
            console.error(error)
        }
    }

    const getGame = async () => {
        try {
            const response = await axios.get(`${backendUrl}/game`)
            console.log(response.data)
            setGame(response.data)
        } catch (error) {
            console.error(error)
        }
    }

    const createVehicle = async () => {
            axios.post(`${backendUrl}/vehicle`, {
                pseudo: pseudoField,
            }).then(response => {
                console.log(response.data)
                setPseudo(response.data.pseudo)
            }).catch(error => {
                console.error(error)
            }).finally(() => {
                setPseudoField('')
            })
    }

    const keydown = (event) => {
        const direction = getArrowKey(event)
        const millis = Date.now() - startTime
        console.log("Millis:" + millis)
        if (direction && millis > delay) {
            console.log(direction)
            setStartTime(Date.now())
            moveVehicle(direction)
        }
    }

    const getArrowKey = (event) => {
        switch (event.key) {
            case 'ArrowUp':
                return 'UP'
            case 'ArrowRight':
                return 'RIGHT'
            case 'ArrowDown':
                return 'DOWN'
            case 'ArrowLeft':
                return 'LEFT'
            default:
                return null
        }
    }

    const moveVehicle = (direction) => {
        console.log(pseudo)
        if (!pseudo) return;
        console.log("Try to move the vehicle");
        axios.put(`${backendUrl}/vehicle`, {
            pseudo: pseudo,
            direction: direction,
        }).then(response => {
            console.log(response);
        }).catch(error => {
            console.error(error);
        });
    }


    useEffect(() => {
        console.log(import.meta.env.VITE_BACKEND_URL)
        const handleKeydown = (event) => {
            keydown(event)
        }
        window.addEventListener('keydown', handleKeydown)
        return () => {
            window.removeEventListener('keydown', handleKeydown)
        }
    }, [pseudo, startTime]);

  return (
        <div className="App" style={{display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center', height: '100vh', width: '100vw'}}>
            { game.x === 0 &&
                <div>
                    <button onClick={createGame}>Create Game</button>
                    <button onClick={getGame}>Join Game</button>
                </div>
            }
            {pseudo.length !== 0 ?
                <div>
                    <h1>Your pseudo: {pseudo}</h1>
                </div>
                :
                game.x !== 0 &&
                <div>
                    <input
                        type="text"
                        value={pseudoField}
                        onChange={(event) => setPseudoField(event.target.value)}
                        placeholder="Pseudo"
                    />
                    <button onClick={createVehicle}>Connect</button>
                </div>
            }
            <div className="game" style={{flex: 1, display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center', width: `100%`}}>
                {game.x !== 0 &&
                Array.from({length: game.y}, (_, i) =>
                    <div key={i} className="row" style={{display: 'flex', flexDirection: 'row', justifyContent: "center", width: `100%`, height: `calc(90% / ${game.y})`}}>
                        {Array.from({length: game.x}, (_, j) =>
                            <div key={j} className="cell" style={{height: `100%`, width: `calc(90% / ${game.x})`, display: 'flex', justifyContent: 'center', alignItems: 'center', border: '1px solid black'}}>
                                {
                                    // if garage, then display a garage emote and the pseudo of all the vehicles in the garage
                                    // else if gas station, then display a gas station emote and the pseudo of all the vehicles in the gas station
                                    // else if vehicle, then display a vehicle emote
                                    // else display nothing
                                }
                                {
                                    game.garages.some(garage => garage.x === j && garage.y === i) ?
                                        <div style={{display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center'}}>
                                            🏠
                                            <div>{game.vehicles.filter(vehicle => vehicle.x === j && vehicle.y === i).map(vehicle => vehicle.pseudo).join(', ')}</div>
                                        </div>
                                        :
                                        game.gasStations.some(gasStation => gasStation.x === j && gasStation.y === i) ?
                                            <div style={{display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center'}}>
                                                ⛽
                                                <div>{game.vehicles.filter(vehicle => vehicle.x === j && vehicle.y === i).map(vehicle => vehicle.pseudo).join(', ')}</div>
                                            </div>
                                            :
                                            game.vehicles.some(vehicle => vehicle.x === j && vehicle.y === i) ?
                                                <div style={{display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center'}}>
                                                    {
                                                        game.vehicles.find(vehicle => vehicle.x === j && vehicle.y === i).direction === "UP" ?
                                                            '⬆️'
                                                            :
                                                            game.vehicles.find(vehicle => vehicle.x === j && vehicle.y === i).direction === "RIGHT" ?
                                                                '➡️'
                                                                :
                                                                game.vehicles.find(vehicle => vehicle.x === j && vehicle.y === i).direction === "DOWN" ?
                                                                    '⬇️'
                                                                    :
                                                                    '⬅️'
                                                    }
                                                    <div>{game.vehicles.find(vehicle => vehicle.x === j && vehicle.y === i).pseudo}</div>
                                                </div>
                                                :
                                                null
                                }
                            </div>
                        )}
                    </div>
                )}
            </div>
        </div>
  )
}

export default App
