# Call Center Simulator

## Overview

The **Call Center Simulation** is a multi-threaded Java-based project that simulates a call center environment with agents serving customers. The simulation creates multiple customer threads that request service, while a greeter thread informs customers of their position in the queue. This project demonstrates the use of multithreading, synchronization, and concurrency management in Java.

## Features

- Simulates a call center with a specified number of agents and customers.
- Implements a queue system where customers wait for an agent to become available.
- Utilizes threading to allow multiple customers to be served concurrently.
- Displays the position of each customer in the dispatch queue.

## How to Use

1. **Clone the Repository**
   ```bash
   git clone https://github.com/egreenfield323/CS316-final-call-center.git
   cd CallCenterSimulation
   ```

2. **Compile the Code**
   Make sure you have Java installed on your machine. Compile the project using:
   ```bash
   javac CallCenter.java
   ```

3. **Run the Simulation**
   Execute the program with the following command:
   ```bash
   java CallCenter
   ```

4. **Observe the Output**
   The console will display messages indicating which agent is serving which customer, as well as the customer's position in the dispatch queue.

## Code Structure

- **CallCenter.java**: Contains the main logic of the simulation, including classes for `Agent`, `Greeter`, and `Customer`.

### Key Classes

- **CallCenter**: The main class that initializes and manages the simulation.
- **Agent**: Represents an agent that serves customers.
- **Greeter**: Handles greeting customers and informing them of their position in the queue.
- **Customer**: Represents a customer requesting service.

## Authors

- **Evan Greenfield** - [egreenfield323](https://github.com/egreenfield323)
- **Spencer Smith** - [spencersmith24](https://github.com/spencersmith24)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
