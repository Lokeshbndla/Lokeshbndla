from datetime import datetime

def insert_order(connection, order):
    """
    Inserts a new order and its details into the database.
    """
    cursor = connection.cursor()

    # Insert into orders table
    order_query = """
        INSERT INTO orders (customer_name, total, datetime)
        VALUES (%s, %s, %s)
    """
    order_data = (order['customer_name'], order['grand_total'], datetime.now())
    cursor.execute(order_query, order_data)
    order_id = cursor.lastrowid

    # Insert into order_details table
    order_details_query = """
        INSERT INTO order_details (order_id, product_id, quantity, total_price)
        VALUES (%s, %s, %s, %s)
    """
    order_details_data = [
        (order_id, detail['product_id'], detail['quantity'], detail['total_price'])
        for detail in order['order_details']
    ]
    cursor.executemany(order_details_query, order_details_data)

    connection.commit()
    cursor.close()
    return order_id

def get_order_details(connection, order_id):
    """
    Fetches details of a specific order.
    """
    cursor = connection.cursor()
    query = """
        SELECT 
            order_details.order_id, 
            order_details.quantity, 
            order_details.total_price, 
            products.name, 
            products.price_per_unit 
        FROM 
            order_details 
        LEFT JOIN 
            products 
        ON 
            order_details.product_id = products.product_id 
        WHERE 
            order_details.order_id = %s
    """
    cursor.execute(query, (order_id,))
    records = [
        {
            'order_id': order_id,
            'quantity': quantity,
            'total_price': total_price,
            'product_name': product_name,
            'price_per_unit': price_per_unit
        }
        for (order_id, quantity, total_price, product_name, price_per_unit) in cursor
    ]
    cursor.close()
    return records

def get_all_orders(connection):
    """
    Fetches all orders with their details.
    """
    cursor = connection.cursor()
    query = "SELECT * FROM orders"
    cursor.execute(query)
    orders = [
        {
            'order_id': order_id,
            'customer_name': customer_name,
            'total': total,
            'datetime': dt
        }
        for (order_id, customer_name, total, dt) in cursor
    ]
    cursor.close()

    # Append order details to each order
    for order in orders:
        order['order_details'] = get_order_details(connection, order['order_id'])

    return orders