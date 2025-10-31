## Tips

- Avoid catching Exception or Throwable unless truly needed
- Use exception messages to guide developers/users 
- Do not swallow exceptions silently (catch (Exception e) {} with nothing inside)
- Validate inputs early and throw meaningful exceptions 
- Use logging (log.error("DB failed", e)) to track issues 
- For APIs: use proper HTTP status codes and error responses

