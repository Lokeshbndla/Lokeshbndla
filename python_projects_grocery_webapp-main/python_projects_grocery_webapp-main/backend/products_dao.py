def get_all_products(connection):
    """
    Fetches all products with their details, including UOM.
    """
    cursor = connection.cursor()
    query = """
        SELECT 
            products.product_id, 
            products.name, 
            products.price_per_unit, 
            uom.uom_name 
        FROM 
            products 
        INNER JOIN 
            uom 
        ON 
            products.uom_id = uom.uom_id
    """
    cursor.execute(query)
    response = [
        {
            'product_id': product_id,
            'name': name,
            'price_per_unit': price_per_unit,
            'uom_name': uom_name
        }
        for (product_id, name, price_per_unit, uom_name) in cursor
    ]
    cursor.close()
    return response

def insert_new_product(connection, product):
    """
    Inserts a new product into the database.
    """
    cursor = connection.cursor()
    query = """
        INSERT INTO products (name, uom_id, price_per_unit)
        VALUES (%s, %s, %s)
    """
    data = (product['product_name'], product['uom_id'], product['price_per_unit'])
    cursor.execute(query, data)
    connection.commit()
    product_id = cursor.lastrowid
    cursor.close()
    return product_id

def delete_product(connection, product_id):
    """
    Deletes a product by its ID.
    """
    cursor = connection.cursor()
    query = "DELETE FROM products WHERE product_id = %s"
    cursor.execute(query, (product_id,))
    connection.commit()
    cursor.close()
    return product_id